package cn.edu.tongji.dwbackend.neo4j.repository;

import cn.edu.tongji.dwbackend.neo4j.entity.MovieNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieNodeRepository extends Neo4jRepository<MovieNode,Long> {
    @Query("MATCH (n:MovieNode) RETURN n")
    MovieNode findNodeById(Long id);
}
