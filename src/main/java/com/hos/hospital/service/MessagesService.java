package com.hos.hospital.service;



import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.MessagesExample;

import java.util.List;

public interface MessagesService {

    int countByExample(MessagesExample example);


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Messages record);

    List<Messages> selectByExample(MessagesExample example);

    Messages selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Messages record);


	List<Messages> selectMessages(Messages message);


	PageInfo<Messages> selectMessagesList(Messages messages, Integer page, Integer size);


	List<Messages> selectMessagesTWO(Messages mess);


	PageInfo<Messages> selectMessagesListDemo(Messages messages, int page, int size);


	List<Messages> selectMessagesPai(Messages messages);


	

}
