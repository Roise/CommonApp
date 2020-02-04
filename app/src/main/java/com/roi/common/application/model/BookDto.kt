package com.roi.common.application.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookDto {
    @Expose
    @SerializedName("searchResult")
    private var searchresult: MutableList<SearchResult>? = null
    @Expose
    @SerializedName("total_page")
    private var totalPage = 0
    @Expose
    @SerializedName("resultMsg")
    private var resultmsg: String? = null
    @Expose
    @SerializedName("resultCode")
    private var resultcode: String? = null


    fun getSearchresult(): MutableList<SearchResult>? {
        return searchresult
    }

    fun setSearchresult(searchresult: MutableList<SearchResult>?) {
        this.searchresult = searchresult
    }

    fun getTotalPage(): Int {
        return totalPage
    }

    fun setTotalPage(totalPage: Int) {
        this.totalPage = totalPage
    }

    fun getResultmsg(): String? {
        return resultmsg
    }

    fun setResultmsg(resultmsg: String?) {
        this.resultmsg = resultmsg
    }

    fun getResultcode(): String? {
        return resultcode
    }

    fun setResultcode(resultcode: String?) {
        this.resultcode = resultcode
    }

    class SearchResult {
        @Expose
        @SerializedName("bookType")
        var booktype: String? = null
        //        @Expose
//        @SerializedName("ebookQR2")
//        var ebookqr2: String? = null
//        @Expose
//        @SerializedName("ebookQR")
//        var ebookqr: String? = null
        @Expose
        @SerializedName("chapter")
        var chapter: List<Chapter>? = null
        @Expose
        @SerializedName("badge")
        var badge: String? = null
        @Expose
        @SerializedName("info_date")
        var infoDate: String? = null
        @Expose
        @SerializedName("info_author")
        var infoAuthor: String? = null
        //        @Expose
//        @SerializedName("firstside")
//        var firstside: String? = null
//        @Expose
//        @SerializedName("file_x")
//        var fileX: String? = null
//        @Expose
//        @SerializedName("file_t")
//        var fileT: String? = null
        @Expose
        @SerializedName("file")
        var file: String? = null
        @Expose
        @SerializedName("thumbnail")
        var thumbnail: String? = null
        @Expose
        @SerializedName("date")
        var date: String? = null
        //        @Expose
//        @SerializedName("INFO_SHELF")
//        var infoShelf: String? = null
        @Expose
        @SerializedName("title")
        var title: String? = null
        //        @Expose
//        @SerializedName("cat_tag_code")
//        var catTagCode: String? = null
        @Expose
        @SerializedName("category_code")
        var categoryCode: String? = null
    }

    class Chapter {
        @Expose
        @SerializedName("page")
        var page: String? = null
        @Expose
        @SerializedName("title")
        var title: String? = null
    }
}
