package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterId
import com.stebakov.holybible.presentation.chapters.ChapterUi

interface ChapterDomainToUiMapper : Abstract.Mapper.Data<ChapterId, ChapterUi>