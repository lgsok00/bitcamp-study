package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;

public interface BoardDao {
  void insert(Board board);

  List<Board> findAll(int category);

  Board findBy(int category, int no);

  int update(Board board);

  int updateCount(Board board);

  int delete(Board board);

  int insertFiles(Board board);

  // 첨부파일 찾기
  AttachedFile findFileBy(int no);

  // deleteFile 추가
  int deleteFile(int fileNo);
}
