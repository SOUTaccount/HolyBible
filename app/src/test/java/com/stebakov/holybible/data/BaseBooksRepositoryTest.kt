package com.stebakov.holybible.data


abstract class BaseBooksRepositoryTest {

    protected inner class TestBookCloudMapper : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id, name)
    }

    protected inner class TestBookCacheMapper : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id, name)
    }
}