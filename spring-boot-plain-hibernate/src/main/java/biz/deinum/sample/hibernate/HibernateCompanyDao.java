package biz.deinum.sample.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marten on 11-01-15.
 */
@Repository
@Transactional
class HibernateCompanyDao implements CompanyDao {

    private final SessionFactory sf;

    @Autowired
    public HibernateCompanyDao(SessionFactory sf) {
        this.sf=sf;
    }

    @Override
    public List<Company> findAll() {
        return sf.getCurrentSession().createQuery("from Company c").list();
    }

    @Override
    public void add(Company c) {
        sf.getCurrentSession().saveOrUpdate(c);
    }

    @Override
    public boolean remove(Company c) {
        sf.getCurrentSession().delete(c);
        return true;
    }
}
