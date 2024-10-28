package com.hos.hospital.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DoctorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DoctorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswoedIsNull() {
            addCriterion("passwoed is null");
            return (Criteria) this;
        }

        public Criteria andPasswoedIsNotNull() {
            addCriterion("passwoed is not null");
            return (Criteria) this;
        }

        public Criteria andPasswoedEqualTo(String value) {
            addCriterion("passwoed =", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedNotEqualTo(String value) {
            addCriterion("passwoed <>", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedGreaterThan(String value) {
            addCriterion("passwoed >", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedGreaterThanOrEqualTo(String value) {
            addCriterion("passwoed >=", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedLessThan(String value) {
            addCriterion("passwoed <", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedLessThanOrEqualTo(String value) {
            addCriterion("passwoed <=", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedLike(String value) {
            addCriterion("passwoed like", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedNotLike(String value) {
            addCriterion("passwoed not like", value, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedIn(List<String> values) {
            addCriterion("passwoed in", values, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedNotIn(List<String> values) {
            addCriterion("passwoed not in", values, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedBetween(String value1, String value2) {
            addCriterion("passwoed between", value1, value2, "passwoed");
            return (Criteria) this;
        }

        public Criteria andPasswoedNotBetween(String value1, String value2) {
            addCriterion("passwoed not between", value1, value2, "passwoed");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andForteIsNull() {
            addCriterion("forte is null");
            return (Criteria) this;
        }

        public Criteria andForteIsNotNull() {
            addCriterion("forte is not null");
            return (Criteria) this;
        }

        public Criteria andForteEqualTo(String value) {
            addCriterion("forte =", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteNotEqualTo(String value) {
            addCriterion("forte <>", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteGreaterThan(String value) {
            addCriterion("forte >", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteGreaterThanOrEqualTo(String value) {
            addCriterion("forte >=", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteLessThan(String value) {
            addCriterion("forte <", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteLessThanOrEqualTo(String value) {
            addCriterion("forte <=", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteLike(String value) {
            addCriterion("forte like", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteNotLike(String value) {
            addCriterion("forte not like", value, "forte");
            return (Criteria) this;
        }

        public Criteria andForteIn(List<String> values) {
            addCriterion("forte in", values, "forte");
            return (Criteria) this;
        }

        public Criteria andForteNotIn(List<String> values) {
            addCriterion("forte not in", values, "forte");
            return (Criteria) this;
        }

        public Criteria andForteBetween(String value1, String value2) {
            addCriterion("forte between", value1, value2, "forte");
            return (Criteria) this;
        }

        public Criteria andForteNotBetween(String value1, String value2) {
            addCriterion("forte not between", value1, value2, "forte");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andHosnameIsNull() {
            addCriterion("hosname is null");
            return (Criteria) this;
        }

        public Criteria andHosnameIsNotNull() {
            addCriterion("hosname is not null");
            return (Criteria) this;
        }

        public Criteria andHosnameEqualTo(String value) {
            addCriterion("hosname =", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameNotEqualTo(String value) {
            addCriterion("hosname <>", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameGreaterThan(String value) {
            addCriterion("hosname >", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameGreaterThanOrEqualTo(String value) {
            addCriterion("hosname >=", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameLessThan(String value) {
            addCriterion("hosname <", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameLessThanOrEqualTo(String value) {
            addCriterion("hosname <=", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameLike(String value) {
            addCriterion("hosname like", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameNotLike(String value) {
            addCriterion("hosname not like", value, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameIn(List<String> values) {
            addCriterion("hosname in", values, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameNotIn(List<String> values) {
            addCriterion("hosname not in", values, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameBetween(String value1, String value2) {
            addCriterion("hosname between", value1, value2, "hosname");
            return (Criteria) this;
        }

        public Criteria andHosnameNotBetween(String value1, String value2) {
            addCriterion("hosname not between", value1, value2, "hosname");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andNumbersIsNull() {
            addCriterion("numbers is null");
            return (Criteria) this;
        }

        public Criteria andNumbersIsNotNull() {
            addCriterion("numbers is not null");
            return (Criteria) this;
        }

        public Criteria andNumbersEqualTo(Integer value) {
            addCriterion("numbers =", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotEqualTo(Integer value) {
            addCriterion("numbers <>", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersGreaterThan(Integer value) {
            addCriterion("numbers >", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("numbers >=", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersLessThan(Integer value) {
            addCriterion("numbers <", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("numbers <=", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersIn(List<Integer> values) {
            addCriterion("numbers in", values, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotIn(List<Integer> values) {
            addCriterion("numbers not in", values, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersBetween(Integer value1, Integer value2) {
            addCriterion("numbers between", value1, value2, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("numbers not between", value1, value2, "numbers");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNull() {
            addCriterion("evaluate is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNotNull() {
            addCriterion("evaluate is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateEqualTo(Integer value) {
            addCriterion("evaluate =", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotEqualTo(Integer value) {
            addCriterion("evaluate <>", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThan(Integer value) {
            addCriterion("evaluate >", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThanOrEqualTo(Integer value) {
            addCriterion("evaluate >=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThan(Integer value) {
            addCriterion("evaluate <", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThanOrEqualTo(Integer value) {
            addCriterion("evaluate <=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateIn(List<Integer> values) {
            addCriterion("evaluate in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotIn(List<Integer> values) {
            addCriterion("evaluate not in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateBetween(Integer value1, Integer value2) {
            addCriterion("evaluate between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotBetween(Integer value1, Integer value2) {
            addCriterion("evaluate not between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSnameIsNull() {
            addCriterion("sname is null");
            return (Criteria) this;
        }

        public Criteria andSnameIsNotNull() {
            addCriterion("sname is not null");
            return (Criteria) this;
        }

        public Criteria andSnameEqualTo(String value) {
            addCriterion("sname =", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotEqualTo(String value) {
            addCriterion("sname <>", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThan(String value) {
            addCriterion("sname >", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThanOrEqualTo(String value) {
            addCriterion("sname >=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThan(String value) {
            addCriterion("sname <", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThanOrEqualTo(String value) {
            addCriterion("sname <=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLike(String value) {
            addCriterion("sname like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotLike(String value) {
            addCriterion("sname not like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameIn(List<String> values) {
            addCriterion("sname in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotIn(List<String> values) {
            addCriterion("sname not in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameBetween(String value1, String value2) {
            addCriterion("sname between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotBetween(String value1, String value2) {
            addCriterion("sname not between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNull() {
            addCriterion("begindate is null");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNotNull() {
            addCriterion("begindate is not null");
            return (Criteria) this;
        }

        public Criteria andBegindateEqualTo(Date value) {
            addCriterionForJDBCDate("begindate =", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotEqualTo(Date value) {
            addCriterionForJDBCDate("begindate <>", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThan(Date value) {
            addCriterionForJDBCDate("begindate >", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begindate >=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThan(Date value) {
            addCriterionForJDBCDate("begindate <", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begindate <=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateIn(List<Date> values) {
            addCriterionForJDBCDate("begindate in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotIn(List<Date> values) {
            addCriterionForJDBCDate("begindate not in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begindate between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begindate not between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNull() {
            addCriterion("enddate is null");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNotNull() {
            addCriterion("enddate is not null");
            return (Criteria) this;
        }

        public Criteria andEnddateEqualTo(Date value) {
            addCriterionForJDBCDate("enddate =", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("enddate <>", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThan(Date value) {
            addCriterionForJDBCDate("enddate >", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enddate >=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThan(Date value) {
            addCriterionForJDBCDate("enddate <", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enddate <=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateIn(List<Date> values) {
            addCriterionForJDBCDate("enddate in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("enddate not in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enddate between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enddate not between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("begintime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("begintime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterionForJDBCTime("begintime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("begintime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterionForJDBCTime("begintime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begintime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterionForJDBCTime("begintime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("begintime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterionForJDBCTime("begintime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("begintime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begintime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("begintime not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterionForJDBCTime("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterionForJDBCTime("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterionForJDBCTime("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterionForJDBCTime("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andPeoplesIsNull() {
            addCriterion("peoples is null");
            return (Criteria) this;
        }

        public Criteria andPeoplesIsNotNull() {
            addCriterion("peoples is not null");
            return (Criteria) this;
        }

        public Criteria andPeoplesEqualTo(Integer value) {
            addCriterion("peoples =", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesNotEqualTo(Integer value) {
            addCriterion("peoples <>", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesGreaterThan(Integer value) {
            addCriterion("peoples >", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesGreaterThanOrEqualTo(Integer value) {
            addCriterion("peoples >=", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesLessThan(Integer value) {
            addCriterion("peoples <", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesLessThanOrEqualTo(Integer value) {
            addCriterion("peoples <=", value, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesIn(List<Integer> values) {
            addCriterion("peoples in", values, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesNotIn(List<Integer> values) {
            addCriterion("peoples not in", values, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesBetween(Integer value1, Integer value2) {
            addCriterion("peoples between", value1, value2, "peoples");
            return (Criteria) this;
        }

        public Criteria andPeoplesNotBetween(Integer value1, Integer value2) {
            addCriterion("peoples not between", value1, value2, "peoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesIsNull() {
            addCriterion("yipeoples is null");
            return (Criteria) this;
        }

        public Criteria andYipeoplesIsNotNull() {
            addCriterion("yipeoples is not null");
            return (Criteria) this;
        }

        public Criteria andYipeoplesEqualTo(Integer value) {
            addCriterion("yipeoples =", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesNotEqualTo(Integer value) {
            addCriterion("yipeoples <>", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesGreaterThan(Integer value) {
            addCriterion("yipeoples >", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesGreaterThanOrEqualTo(Integer value) {
            addCriterion("yipeoples >=", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesLessThan(Integer value) {
            addCriterion("yipeoples <", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesLessThanOrEqualTo(Integer value) {
            addCriterion("yipeoples <=", value, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesIn(List<Integer> values) {
            addCriterion("yipeoples in", values, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesNotIn(List<Integer> values) {
            addCriterion("yipeoples not in", values, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesBetween(Integer value1, Integer value2) {
            addCriterion("yipeoples between", value1, value2, "yipeoples");
            return (Criteria) this;
        }

        public Criteria andYipeoplesNotBetween(Integer value1, Integer value2) {
            addCriterion("yipeoples not between", value1, value2, "yipeoples");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}