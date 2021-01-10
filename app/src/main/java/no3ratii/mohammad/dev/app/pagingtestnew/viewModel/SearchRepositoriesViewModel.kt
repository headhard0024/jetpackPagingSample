/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no3ratii.mohammad.dev.app.pagingtestnew.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import no3ratii.mohammad.dev.app.pagingtestnew.base.Retrofit
import no3ratii.mohammad.dev.app.pagingtestnew.model.Repo
import no3ratii.mohammad.dev.app.pagingtestnew.model.Repository.GithubRepository
import no3ratii.mohammad.dev.app.pagingtestnew.model.connection.api.GithubService

@ExperimentalCoroutinesApi
class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }

        val newResult =  repository.getSearchResultStream(queryString)
                // پاک کردن حافظه viewmodel
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}