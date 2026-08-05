from report import top_words


def test_예시_리뷰의_상위_3개_단어를_반환한다():
    result = top_words("Good good service. Fast delivery, good price!")

    assert result == ["good:3", "delivery:1", "fast:1"]


def test_대소문자를_구분하지_않고_소문자로_표기한다():
    assert top_words("Apple apple APPLE banana") == ["apple:3", "banana:1"]


def test_단어_앞뒤의_문장부호를_무시한다():
    assert top_words("wow! wow? wow. nice,") == ["wow:3", "nice:1"]


def test_빈도가_같으면_사전순으로_앞선_단어를_우선한다():
    assert top_words("b a c") == ["a:1", "b:1", "c:1"]


def test_서로_다른_단어가_3개_미만이면_있는_만큼만_반환한다():
    assert top_words("only only") == ["only:2"]
