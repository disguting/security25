package furman.security25.item;

/*
@author   User
@project   security25
@class  Item
@version  1.0.0
@since 18.11.2025 - 06.07
*/

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString


public class Cat extends AuditMetaData{
    @Id
    private String id;
    private String name;
    private String description;

    public Cat(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Cat cat)) return false;

        return getId().equals(cat.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
