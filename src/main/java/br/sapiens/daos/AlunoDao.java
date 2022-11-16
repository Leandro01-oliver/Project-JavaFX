package br.sapiens.daos;

import br.sapiens.configs.ConexaoSingleton;
import br.sapiens.models.Aluno;
import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlunoDao implements CrudRepository<Aluno,Integer>{
    private final Connection conn;

    public AlunoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public <S extends Aluno> S save(S entity) throws SQLException {
        if(entity.getId() == null)
            return insertInto(entity);
        else
            return update(entity);
    }

    private <S extends Aluno> S update(S entity) throws SQLException{
        String sql = "UPDATE aluno SET nome = ?, dataNascimento = ?, curso = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,entity.getNome());
        pstmt.setString(2,entity.getDataNascimento().toString());
        pstmt.setString(3,entity.getCurso().toString());
        pstmt.setString(4,entity.getId().toString());
        pstmt.executeUpdate();
        return entity;
    }

    private <S extends Aluno> S insertInto(S entity) throws SQLException{
        String sql = "Insert into aluno(nome, dataNascimento, curso ) values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,entity.getNome());
        pstmt.setString(2,entity.getDataNascimento().toString());
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
    public <S extends Aluno> Iterable<Aluno> saveAll(Iterable<S> entities) throws SQLException {

        ArrayList lista = new ArrayList();
        for(S entity : entities) {
            lista.add(save(entity));
        }
        return lista;
    }

    @Override
    public Optional<Aluno> findById(Integer id) throws SQLException {
        List<Aluno> resultados = findAllById(List.of(id));
        if(resultados == null || resultados.size() != 1)
            throw new SQLException("Erro ao buscar valores, não existe somente um resultado! Size "+resultados.size());
        return Optional.ofNullable(resultados.get(0));
    }

    @Override
    public Iterable<Aluno> findAllById(Iterable<Integer> integers) throws SQLException {
        return null;
    }

    @Override
    public void delete(Aluno entity) throws SQLException {

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }

    @Override
    public void deleteAll(Iterable<? extends Aluno> entities) {

    }
}
