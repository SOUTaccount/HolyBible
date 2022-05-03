package com.stebakov.holybible.data


abstract class BaseBooksRepositoryTest {

    protected class TestToBookMapper : ToBookMapper{
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
//
//    protected inner class TestBookCloudMapper : ToBookMapper {
//        override fun map(id: Int, name: String) = BookData(id, name)
//    }
//
//    protected inner class TestBookCacheMapper : ToBookMapper {
//        override fun map(id: Int, name: String) = BookData(id, name)
//    }
}