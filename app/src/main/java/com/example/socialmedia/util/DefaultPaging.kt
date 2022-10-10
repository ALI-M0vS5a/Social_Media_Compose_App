package com.example.socialmedia.util


class DefaultPaging<T>(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextPage: Int) -> Resource<List<T>>,
    private val onError: suspend (UiText) -> Unit,
    private val onSuccess: (items: List<T>) -> Unit
): Paging<T> {
    private var page = 0

    override suspend fun loadNextItems() {
        onLoadUpdated(true)
        when(val result = onRequest(page)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                page++
                onSuccess(items)
                onLoadUpdated(false)
            }
            is Resource.Error -> {
                onError(result.uiText ?: UiText.unknownError())
            }
        }

    }
}