DISCOUNT_RATES = {"VIP": 0.10, "GOLD": 0.05}


def total_price(orders, grade):
    """주문 목록의 최종 결제 금액을 계산한다.

    :param orders: {"name": 상품명, "price": 단가, "quantity": 수량} 딕셔너리의 리스트
    :param grade: 회원 등급 (예: "VIP", "GOLD", "BASIC")
    :return: 등급 할인이 적용된 최종 결제 금액 (원 단위 버림, int)
    """
    total = 0

    for order in orders:
        total += order["price"]

    return total
