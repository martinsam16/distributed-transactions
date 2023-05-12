package pe.martinsam.catalogproducts.domain.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class Category {

    private String id;

    public static Category fromString(String id){
        return Category.builder().id(id).build();
    }
}
