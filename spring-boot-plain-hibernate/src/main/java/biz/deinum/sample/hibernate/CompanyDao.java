package biz.deinum.sample.hibernate;

import java.util.List;

/**
 * Created by marten on 11-01-15.
 */
public interface CompanyDao {

    List<Company> findAll();
    void add(Company c);
    boolean remove(Company c);
}
