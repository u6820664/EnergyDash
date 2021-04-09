package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface CompanyImageDao {
    Map findImage(@Param("companyName") String companyName);
}
