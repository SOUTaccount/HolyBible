package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.presentation.chapters.ChaptersUi

abstract class ChaptersDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, ChaptersUi>(resourceProvider)