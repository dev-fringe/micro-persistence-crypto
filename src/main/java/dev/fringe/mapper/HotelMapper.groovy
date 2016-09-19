package dev.fringe.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

import dev.fringe.domain.Hotel
import dev.fringe.crypto.encrypt.MyBatisHandlerStringEncryptor

@Mapper
interface HotelMapper {
	@Insert("""<script>
        insert into hotel(
          city
        , name
        , address
        , zip
        ) values (
          #{city}
        , #{name,jdbcType=BINARY,javaType=java.lang.String,typeHandler=dev.fringe.crypto.encrypt.MyBatisHandlerStringEncryptor}
        , #{address}
        , #{zip}
        )
	</script>""")
	void insertHotel(Hotel hotel);

	@Select("""<script>
        select 
          city
        , name
        , address
        , zip
        from hotel where city = #{city}
	</script>""")
	@Results(value = [
		@Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, typeHandler=MyBatisHandlerStringEncryptor, id=true),
		@Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
		@Result(column="address", property="address", jdbcType=JdbcType.VARCHAR ),
		@Result(column="zip", property="zip", jdbcType=JdbcType.VARCHAR )
	])
	Hotel selectByCityId(Hotel hotel);
}
