package com.mahshad.home

sealed interface SearchResultUiState {
    // one state is whether the search ac tion buttons are clicked or not, first value is false
    // click takes you to the show of suggestion box and unclick takes you to the content screen
    // click is happened with a query

}