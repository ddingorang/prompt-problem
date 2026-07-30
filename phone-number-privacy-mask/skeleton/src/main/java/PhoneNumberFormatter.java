public class PhoneNumberFormatter {

    /**
     * 고객 화면에 표시할 전화번호를 반환한다.
     *
     * @param phoneNumber 하이픈으로 구분된 전화번호
     * @return 개인정보가 마스킹된 전화번호
     */
    public String maskPhoneNumber(String phoneNumber) {
        return phoneNumber.substring(0, 4)
                + "****"
                + phoneNumber.substring(8);
    }
}