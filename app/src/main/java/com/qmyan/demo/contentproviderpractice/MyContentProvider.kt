package com.qmyan.demo.contentproviderpractice

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import android.util.Log
import com.qmyan.demo.databasepractice.MyDataBaseHelper

class MyContentProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.qmyan.demo.provider"
        private const val BOOK_DIR = 0
        private const val BOOK_ITEM = 1
        private const val CATEGORY_DIR = 2
        private const val CATEGORY_ITEM = 3
        private const val MIME_TYPE_DIR_PREFIX = "vnd.android.cursor.dir/vnd.$AUTHORITY"
        private const val MIME_TYPE_ITEM_PREFIX = "vnd.android.cursor.item/vnd.$AUTHORITY"
        private const val TAG = "MyContentProvider"
    }

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(AUTHORITY, "book", BOOK_DIR)
        matcher.addURI(AUTHORITY, "book/#", BOOK_ITEM)
        matcher.addURI(AUTHORITY, "category", CATEGORY_DIR)
        matcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM)
        matcher
    }
    private lateinit var dbHelper: MyDataBaseHelper

    override fun onCreate() = context?.let {
        dbHelper = MyDataBaseHelper(it, "demo", null, 3)
        true
    } ?: false

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        BOOK_DIR -> "$MIME_TYPE_DIR_PREFIX.$MyDataBaseHelper.TABLE_NAME_BOOK"
        BOOK_ITEM -> "$MIME_TYPE_ITEM_PREFIX.$MyDataBaseHelper.TABLE_NAME_BOOK"
        CATEGORY_DIR -> "$MIME_TYPE_DIR_PREFIX.$MyDataBaseHelper.TABLE_NAME_CATEGORY"
        CATEGORY_ITEM -> "$MIME_TYPE_ITEM_PREFIX.$MyDataBaseHelper.TABLE_NAME_CATEGORY"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            BOOK_DIR, BOOK_ITEM -> {
                val newBookId = db.insert(MyDataBaseHelper.TABLE_NAME_BOOK, null, values)
                Uri.parse("content://$AUTHORITY/$MyDataBaseHelper.TABLE_NAME_BOOK/$newBookId")
            }
            CATEGORY_DIR, CATEGORY_ITEM -> {
                val newCategoryId = db.insert(MyDataBaseHelper.TABLE_NAME_CATEGORY, null, values)
                Uri.parse("content://$AUTHORITY/$MyDataBaseHelper.TABLE_NAME_CATEGORY/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper.let {
        Log.d(TAG, "query: uri---${uri.toString()}")
        Log.d(TAG, "query: uriMatcher---${uriMatcher.match(uri)}")
        val db = it.writableDatabase
        val cursorReturn = when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                db.query(MyDataBaseHelper.TABLE_NAME_BOOK, projection, selection, selectionArgs, null, null, sortOrder)
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                Log.d(TAG, "query: bookId---${bookId}")
                db.query(MyDataBaseHelper.TABLE_NAME_BOOK, projection, "id = ?", arrayOf(bookId), null, null, sortOrder)
            }
            CATEGORY_DIR -> {
                db.query(MyDataBaseHelper.TABLE_NAME_CATEGORY, projection, selection, selectionArgs, null, null, sortOrder)
            }
            CATEGORY_ITEM -> {
                val categoryId = uri.pathSegments[1]
                db.query(MyDataBaseHelper.TABLE_NAME_CATEGORY, projection, "id = ?", arrayOf(categoryId), null, null, sortOrder)
            }
            else -> null
        }
        cursorReturn
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper.let {
        val db = it.writableDatabase
        val effectedRowsReturn = when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                db.update(MyDataBaseHelper.TABLE_NAME_BOOK, values, selection, selectionArgs)
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                db.update(MyDataBaseHelper.TABLE_NAME_BOOK, values, "where id = ?", arrayOf(bookId))
            }
            CATEGORY_DIR -> {
                db.update(MyDataBaseHelper.TABLE_NAME_CATEGORY, values, selection, selectionArgs)
            }
            CATEGORY_ITEM -> {
                val categoryId = uri.pathSegments[1]
                db.update(MyDataBaseHelper.TABLE_NAME_CATEGORY, values, "where id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        effectedRowsReturn
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?)= dbHelper.let {
        val db = it.writableDatabase
        val effectedRowsReturn = when (uriMatcher.match(uri)) {
            BOOK_DIR -> {
                db.delete(MyDataBaseHelper.TABLE_NAME_BOOK, selection, selectionArgs)
            }
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                db.delete(MyDataBaseHelper.TABLE_NAME_BOOK, "where id = ?", arrayOf(bookId))
            }
            CATEGORY_DIR -> {
                db.delete(MyDataBaseHelper.TABLE_NAME_CATEGORY, selection, selectionArgs)
            }
            CATEGORY_ITEM -> {
                val categoryId = uri.pathSegments[1]
                db.delete(MyDataBaseHelper.TABLE_NAME_CATEGORY, "where id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        effectedRowsReturn
    }
}
