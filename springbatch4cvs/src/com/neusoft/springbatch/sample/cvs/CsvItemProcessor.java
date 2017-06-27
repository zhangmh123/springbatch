package com.neusoft.springbatch.sample.cvs;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * ItemProcessor�ࡣ
 */
@Component("csvItemProcessor")
public class CsvItemProcessor implements ItemProcessor<Student, Student> {

    /**
     * ��ȡ�������ݽ��м򵥵Ĵ���
     * 
     * @param student
     *            ����ǰ�����ݡ�
     * @return ���������ݡ�
     * @exception Exception
     *                �����Ƿ������κ��쳣��
     */
    @Override
    public Student process(Student student) throws Exception {
        /* �ϲ�ID������ */
        student.setName(student.getID() + "--" + student.getName());
        /* �����2 */
        student.setAge(student.getAge() + 2);
        /* ������10 */
        student.setScore(student.getScore() + 10);
        /* �������Ľ�����ݸ�writer */
        return student;
    }
}
