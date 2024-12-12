package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.CustomerDao;
import com.bank.dao.util.ConnectionProvider;
import com.bank.models.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private static boolean isTableExists = false;
	private String[] columns=null;
	private void createTable() {
		try (Connection con = ConnectionProvider.getMySqlConnection(); Statement st = con.createStatement()) {
			st.execute("""
					create table if not exists Customers(
					custId int primary key auto_increment,
					custName varchar(50),
					mobNo bigint,
					salary double,
					isMarried boolean,
					dob date,
					joinedAt varchar(60)
					);
					""");
			System.out.println("Table created!");
			isTableExists = true;

		} catch (SQLException e) {
			e.printStackTrace();
			isTableExists = false;
		}

	}

	public CustomerDaoImpl() {
		if (!isTableExists) {
			System.out.println("table not existed!");
			createTable();
		}
	}

	@Override
	public boolean registerCustomer(Customer newCustomer) {

		try (Connection con = ConnectionProvider.getMySqlConnection()) {
			
		PreparedStatement ps=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?)");

		ps.setInt(1,newCustomer.getEmpId());
		ps.setString(2,newCustomer.getName());
		ps.setLong(3,newCustomer.getMobileNo());
		ps.setDouble(4,newCustomer.getSalary());
		ps.setBoolean(5,newCustomer.getIsMarried());
		ps.setDate(6,java.sql.Date.valueOf(newCustomer.getDob()));
		ps.setString(7,newCustomer.getJoinedAt());

		System.out.println(ps);
		int rowsAffected = ps.executeUpdate();// DML -> insert,update,delete
		ps.close();
		return rowsAffected >0 ? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {


		try (Connection con = ConnectionProvider.getMySqlConnection()) {
			PreparedStatement ps=con.prepareStatement("update customers set custname=?,mobno=?,salary=?,ismarried=?,dob=? where custId=?;");

			
		ps.setString(1,customer.getName());
		ps.setLong(2,customer.getMobileNo());
		ps.setDouble(3,customer.getSalary());
		ps.setBoolean(4,customer.getIsMarried());
		ps.setDate(5,java.sql.Date.valueOf(customer.getDob()));
		ps.setInt(6,customer.getEmpId());
		
		System.out.println(ps);
		int rowsAffected = ps.executeUpdate();// DML -> insert,update,delete
		ps.close();
		return rowsAffected >0 ? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer getCustomerById(Long id) {

		try (Connection con = ConnectionProvider.getMySqlConnection()) {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customers where custId="+id+";");
			rs.next();
			Customer tempCust=new Customer();
			tempCust.setEmpId(rs.getInt(1));
			tempCust.setName(rs.getString("custName"));
			tempCust.setMobileNo(rs.getLong(3));
			tempCust.setSalary(rs.getDouble(4));
			tempCust.setIsMarried(rs.getBoolean(5));
			tempCust.setDob(rs.getDate(6).toLocalDate());
			tempCust.setJoinedAt(rs.getString(7));
			return tempCust;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer getCustomerByMobileNo(Long mobNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(Long id) {

		try (Connection con = ConnectionProvider.getMySqlConnection()) {
			PreparedStatement ps=con.prepareStatement("delete from customers where custId=?;");
		ps.setLong(1,id);
		System.out.println(ps);
		int rowsAffected = ps.executeUpdate();// DML -> insert,update,delete
		ps.close();
		return rowsAffected >0 ? true : false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> custList=new ArrayList<Customer>();
		try (Connection con = ConnectionProvider.getMySqlConnection()) {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customers;");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			columns=new String[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++) 
			{
				columns[i]=rsmd.getColumnName(i+1);
			}
			
			while(rs.next())
			{				
				Customer tempCust=new Customer();
				tempCust.setEmpId(rs.getInt(1));
				tempCust.setName(rs.getString("custName"));
				tempCust.setMobileNo(rs.getLong(3));
				tempCust.setSalary(rs.getDouble(4));
				tempCust.setIsMarried(rs.getBoolean(5));
				tempCust.setDob(rs.getDate(6).toLocalDate());
				tempCust.setJoinedAt(rs.getString(7));
				custList.add(tempCust);
			}
			
			return custList;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String[] getAllColumns() {
		return columns;
	}

}
