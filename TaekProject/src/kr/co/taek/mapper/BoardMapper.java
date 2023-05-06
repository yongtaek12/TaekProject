package kr.co.taek.mapper;

import org.apache.ibatis.annotations.Insert;

import kr.co.taek.beans.ContentBean;
/* jdbcType=VARCHAR 쓰는 이유 테이블상 null 허용이지만 null 값을 넣으면 마이바티스에 오류가 발생해여 잡기위해 설정*/
public interface BoardMapper {
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
			"content_file, content_writer_idx, content_board_idx, content_date) " +
			"values (content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
}
