package net.developia.spring02.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	public String getTime2(); // 설정파일에서 가져오게 만들 것
}
