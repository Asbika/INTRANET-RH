package com.giantLink.Hiring.recrutementservice.services;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CrudServices<RQ,RS,RQU,ID>{
        public RS add(RQ request);
        public List<RS> getAll();
        public RS get(ID id);
        RS update(RQU request, ID id);
        public void delete(ID id);
}
