package br.sapiens.daos;


import br.sapiens.models.Disciplina;
import br.sapiens.models.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class MatriculaDao implements CrudRepository<Matricula,Integer>{

    private final Connection conn;

    public MatriculaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public <S extends Matricula> S save(S entity) throws SQLException {
        if(entity.getAluno() == null && entity.getDisciplina() == null)
            return insertInto(entity);
        else
            return update(entity);
    }

    private <S extends Matricula> S update(S entity) throws SQLException{
        String sql = "UPDATE disciplina SET periodo = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,entity.getPeriodo().toString());
        pstmt.executeUpdate();
        return entity;
    }

    private <S extends Matricula> S insertInto(S entity) throws SQLException{
        String sql = "Insert into matricula(disciplina_id, aluno_id, periodo ) values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,entity.getDisciplina().getId().toString());
        pstmt.setString(2,entity.getAluno().getId().toString());
        pstmt.setString(3,entity.getPeriodo().toString());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0)
            throw new SQLException("Falha, nenhuma linha foi inserida");
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        generatedKeys.next();
        entity.setId(generatedKeys.getString(1));
        return entity;
    }

    @Override
    public <S extends Matricula> Iterable<Matricula> saveAll(Iterable<S> entities) throws SQLException {
        ArrayList lista = new ArrayList();
        for(S entity : entities) {
            lista.add(save(entity));
        }
        return lista;
    }

    @Override
    public Optional<Matricula> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Iterable<Matricula> findAllById(Iterable<Integer> integers) throws SQLException {
        return null;
    }

    @Override
    public void delete(Matricula entity) throws SQLException {

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }

    @Override
    public void deleteAll(Iterable<? extends Matricula> entities) {

    }
}
