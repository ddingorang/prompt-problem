from billing import total_price


def test_수량을_반영해_합계를_계산한다():
    orders = [{"name": "americano", "price": 4500, "quantity": 2}]

    assert total_price(orders, "BASIC") == 9000


def test_VIP_등급은_10퍼센트_할인한다():
    orders = [{"name": "latte", "price": 5000, "quantity": 2}]

    assert total_price(orders, "VIP") == 9000


def test_GOLD_등급은_5퍼센트_할인한다():
    orders = [{"name": "latte", "price": 4000, "quantity": 1}]

    assert total_price(orders, "GOLD") == 3800


def test_할인_후_금액은_원_단위로_버림한다():
    orders = [{"name": "cookie", "price": 1111, "quantity": 1}]

    # 1111 × 0.95 = 1055.45 → 1055
    assert total_price(orders, "GOLD") == 1055


def test_할인이_없는_등급은_합계를_그대로_반환한다():
    orders = [{"name": "tea", "price": 3000, "quantity": 3}]

    assert total_price(orders, "BASIC") == 9000


def test_여러_항목의_금액을_모두_더한다():
    orders = [
        {"name": "americano", "price": 4500, "quantity": 1},
        {"name": "cookie", "price": 2500, "quantity": 2},
    ]

    assert total_price(orders, "BASIC") == 9500


def test_빈_주문_목록은_0원이다():
    assert total_price([], "VIP") == 0
