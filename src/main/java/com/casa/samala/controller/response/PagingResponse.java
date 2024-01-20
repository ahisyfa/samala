package com.casa.samala.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: PagingResponse.java, v 0.1 2024-01-20  15.53 Ahmad Isyfalana Amin Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse {

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;

}