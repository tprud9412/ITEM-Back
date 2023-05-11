package kit.item.domain.mysql.it;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "BRAND")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", nullable = false)
    private Long id;

    @Column(name = "brand_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<BrandProduct> brandProducts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<CategoryBrand> categoryBrands = new ArrayList<>();
}