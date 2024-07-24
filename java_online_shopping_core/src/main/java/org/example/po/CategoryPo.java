package org.example.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@ToString(callSuper = true)
@TableName("home_page")
public class CategoryPo {

    @NotEmpty(message = "id cannot be empty")
    private Integer id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String categoryName;
    
    private Integer parentId;

    @Transient
    private List<CategoryPo> children;

    public static CategoryPo buildCategoryTree(List<CategoryPo> resps) {
        CategoryPo rootNode = resps.stream()
                .filter(f -> 0 == (f.getParentId()))
                .findFirst().orElse(null);
        if (rootNode == null) {
            return null;
        }
        Map<Integer, List<CategoryPo>> groupedByParentCode = resps.stream()
                .collect(Collectors.groupingBy(CategoryPo::getParentId));
        return buildTree(rootNode, groupedByParentCode);
    }

    private static CategoryPo buildTree(CategoryPo parentNode, Map<Integer, List<CategoryPo>> groupedByParentCode) {
        List<CategoryPo> children = groupedByParentCode.getOrDefault(parentNode.getId(), Collections.emptyList());

        if (children != null) {
            parentNode.setChildren(children.stream()
                    .map(child -> buildTree(child, groupedByParentCode))
                    .collect(Collectors.toList()));
        }

        return parentNode;
    }
}
