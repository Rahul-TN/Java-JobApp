package com.RTN.JobApp.repo;

import com.RTN.JobApp.model.JobPost;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepo {

    @Getter
    @Setter
    @Autowired
    private JdbcTemplate jdbc;

    public void addJob(JobPost jobPost)
    {
        String query="Insert into jobs(postId,postProfile,postDesc,reqExperience,postTechStack) values(?,?,?,?,?)";
        String tecstackJson=new Gson().toJson(jobPost.getPostTechStack());

        int rows=jdbc.update(query,jobPost.getPostId(),jobPost.getPostProfile(),jobPost.getPostDesc(),jobPost.getReqExperience(),tecstackJson);
        System.out.println(rows+"affected");
    }
    public List<JobPost> getAllJobs()
    {
            String sql="select * from jobs";

            return jdbc.query(sql,new RowMapper<JobPost>()
            {


                @Override
                public JobPost mapRow(ResultSet rs, int rowNum) throws SQLException {
                    JobPost setjob=new JobPost();

                    setjob.setPostId(rs.getInt("postId"));
                    setjob.setPostProfile(rs.getString("postProfile"));
                    setjob.setPostDesc(rs.getString("postDesc"));
                    setjob.setReqExperience(rs.getInt("reqExperience"));
                    String listTechStack=rs.getString(5);

                    List<String> techStack=new Gson().fromJson(listTechStack,List.class);

                    setjob.setPostTechStack(techStack);

                    return setjob;

                }
            });

    }

}
