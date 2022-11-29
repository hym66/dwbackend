package cn.edu.tongji.dwbackend.neo4j.service;

import cn.edu.tongji.dwbackend.neo4j.entity.MovieNode;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    MovieNode findNodeById(Long id);
}
