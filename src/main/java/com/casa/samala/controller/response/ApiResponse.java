package com.casa.samala.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * BaseResponse
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: BaseResponse.java, v 0.1 2024-01-19  19.09 Ahmad Isyfalana Amin Exp $
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 2193524688135242201L;

    private boolean success;

    private int errorCode;

    private String errorMessage;

    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PagingResponse paging;

    public void setResponseStatusInfo(ApiResponseStatusEnum responseStatusEnum) {
        this.success = responseStatusEnum.isSuccess();
        this.errorCode = responseStatusEnum.getErrorCode();
        this.errorMessage = responseStatusEnum.getErrorMessage();
    }

}