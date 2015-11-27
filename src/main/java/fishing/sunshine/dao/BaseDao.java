package fishing.sunshine.dao;

import org.apache.ibatis.session.SqlSession;

/**
 * Created by sunshine on 11/27/15.
 */
public class BaseDao {
    protected SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
