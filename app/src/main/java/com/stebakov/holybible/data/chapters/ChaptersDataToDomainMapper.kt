package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.chapters.ChaptersDomain
import java.lang.Exception

abstract class ChaptersDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<ChapterData>, ChaptersDomain>()