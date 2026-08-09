import pytest
from pagination import get_page

def test_get_page_first():
    items = [1, 2, 3, 4, 5, 6, 7]
    assert get_page(items, 1, 5) == [1, 2, 3, 4, 5]

def test_get_page_second():
    items = [1, 2, 3, 4, 5, 6, 7]
    assert get_page(items, 2, 5) == [6, 7]

def test_get_page_out_of_bounds():
    items = [1, 2, 3, 4, 5, 6, 7]
    assert get_page(items, 3, 5) == []
    assert get_page(items, 10, 5) == []

def test_get_page_empty_items():
    assert get_page([], 1, 10) == []
    
def test_get_page_exact_match():
    items = [1, 2, 3, 4, 5]
    assert get_page(items, 1, 5) == [1, 2, 3, 4, 5]
    assert get_page(items, 2, 5) == []
