package pe.martinsam.catalogproducts.domain.model.product.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    DRAFT("draft"),
    SOLICITED("solicited"),
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected"),
    PUBLISHED("published"),
    UNPUBLISHED("unpublished"),
    ARCHIVED("archived"),
    UNARCHIVED("unarchived"),
    DELETED("deleted"),
    LAST_UNITS("last_units"),
    LAST_AVAILABLE("last_available"),
    SOLD_OUT("sold_out");

    public final String value;

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