import pytest
from schedule import is_available

def test_is_available_no_overlap():
    existing = [(9, 10), (11, 12)]
    new = (10, 11)
    assert is_available(existing, new) is True

def test_is_available_overlap_start():
    existing = [(10, 12), (14, 16)]
    new = (11, 13)
    assert is_available(existing, new) is False

def test_is_available_overlap_end():
    existing = [(13, 15)]
    new = (12, 14)
    assert is_available(existing, new) is False

def test_is_available_overlap_contain():
    existing = [(10, 11)]
    new = (9, 12)
    assert is_available(existing, new) is False

def test_is_available_overlap_inside():
    existing = [(9, 12)]
    new = (10, 11)
    assert is_available(existing, new) is False

def test_is_available_empty_existing():
    existing = []
    new = (10, 12)
    assert is_available(existing, new) is True

def test_is_available_exact_touch_start():
    existing = [(10, 12)]
    new = (8, 10)
    assert is_available(existing, new) is True

def test_is_available_exact_touch_end():
    existing = [(10, 12)]
    new = (12, 14)
    assert is_available(existing, new) is True
