package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.chapters.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper.Data<ChapterId, ChapterDomain>