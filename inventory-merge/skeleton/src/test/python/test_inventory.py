import pytest
from inventory import merge_items

def test_merge_items_basic():
    current = {"hp_potion": 3, "sword": 1}
    new = [{"name": "hp_potion", "quantity": 2}, {"name": "shield", "quantity": 1}]
    result = merge_items(current, new)
    assert result == {"hp_potion": 5, "sword": 1, "shield": 1}

def test_merge_items_remove_zero_or_negative():
    current = {"potion": 5, "sword": 1}
    new = [{"name": "potion", "quantity": 5}, {"name": "sword", "quantity": -1}, {"name": "shield", "quantity": -2}]
    result = merge_items(current, new)
    assert result == {"potion": 10}

def test_merge_items_new_only():
    current = {}
    new = [{"name": "gem", "quantity": 1}]
    result = merge_items(current, new)
    assert result == {"gem": 1}

def test_merge_items_empty_new():
    current = {"gold": 100}
    new = []
    result = merge_items(current, new)
    assert result == {"gold": 100}

def test_merge_items_multiple_same_items_in_new():
    current = {"apple": 1}
    new = [{"name": "apple", "quantity": 2}, {"name": "apple", "quantity": -1}]
    result = merge_items(current, new)
    assert result == {"apple": 2}
