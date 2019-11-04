package fj.king.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import fj.king.model.Identity;

@Mapper
public interface IdentityMapper {
	public void insert(Identity identity);
	public void update(Identity identity);
	public void delete(int id);
	public List<Identity> findAll(@Param("start") int start, @Param("size") int size);
	public Identity findById(int id);
	public int countTotalRecords();
	public void insertList(List<Identity> identities);
}
