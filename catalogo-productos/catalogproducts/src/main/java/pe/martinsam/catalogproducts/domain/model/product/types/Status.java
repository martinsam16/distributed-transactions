package pe.martinsam.catalogproducts.domain.model.product.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    DELETED("deleted", -1),
    DRAFT("draft", 0),
    SOLICITED("solicited", 1),
    PENDING("pending", 2),
    APPROVED("approved", 3),
    REJECTED("rejected", -3),
    PUBLISHED("published", 4),
    UNPUBLISHED("unpublished", -4),



    LAST_UNITS("last_units", 5),
    LAST_AVAILABLE("last_available", 6),
    SOLD_OUT("sold_out", 7);

    public final String value;
    public final Integer phase;

    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + allowedValues());
    }

    private static String allowedValues() {
        StringBuilder sb = new StringBuilder();
        for (Status status : Status.values()) {
            sb.append(status.value).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

}