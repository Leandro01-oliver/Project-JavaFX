package br.sapiens.daos;


import br.sapiens.models.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DisciplinaDao implements CrudRepository<Disciplina,Integer>{
    private final Connection conn;

    public DisciplinaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public <S extends Disciplina> S save(S entity) throws SQLException {
        if(entity.getId() == null)
            return insertInto(entity);
        else
            return update(entity);
    }

    private <S extends Disciplina> S update(S entity) throws SQLException{
        String sql = "UPDATE disciplina SET descricao = ?, credito = ?, curso = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,entity.getDescricao());
        pstmt.setString(2, String.valueOf(entity.getCreditos()));
        pstmt.setString(3,entity.getCurso().toString());
        pstmt.setString(4,entity.getId().toString());
        pstmt.executeUpdate();
        return entity;
    }

    private <S extends Disciplina> S insertInto(S entity) throws SQLException{
        String sql = "Insert into disciplina(descricao, credito, curso ) values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,entity.getDescricao());
        pstmt.setString(2, String.valueOf(entity.getCreditos()));
        pstmt.setString(3,entity.getCurso().toString());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0)
            throw new SQLException("Falha, nenhuma linha foi inserida");
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        generatedKeys.next();
        entity.setId(generatedKeys.getInt(1));
        return entity;
    }

    @Override
    public <S extends Disciplina> Iterable<Disciplina> saveAll(Iterable<S> entities) throws SQLException {
        ArrayList lista = new ArrayList();
        for(S entity : entities) {
            lista.add(save(entity));
        }
        return lista;
    }

    @Override
    public Optional<Disciplina> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Disciplina> findAllById(Iterable<Integer> integers) throws SQLException {
        return null;
    }

    @Override
    public void delete(Disciplina entity) throws SQLException {

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }

    @Override
    public void deleteAll(Iterable<? extends Disciplina> entities) {

    }
}
