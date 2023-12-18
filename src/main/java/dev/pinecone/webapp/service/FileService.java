package dev.pinecone.webapp.service;

import dev.pinecone.webapp.exception.GCPFileUploadException;
import dev.pinecone.webapp.exception.GenericException;
import dev.pinecone.webapp.model.dto.InputFileDto;
import dev.pinecone.webapp.model.error.ErrorEnum;
import dev.pinecone.webapp.util.DataBucketUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final DataBucketUtil dataBucketUtil;

    public InputFileDto uploadFiles(MultipartFile file) {

        List<InputFileDto> inputFiles = new ArrayList<>();

            String originalFileName = file.getOriginalFilename();
            if(originalFileName == null){
                throw new GenericException(ErrorEnum.FILE_BAD_REQUEST);
            }
            Path path = new File(originalFileName).toPath();

            try {
                String contentType = Files.probeContentType(path);
                InputFileDto inputFileDto = dataBucketUtil.uploadFile(file, originalFileName, contentType);

                if (StringUtils.isNotBlank(inputFileDto.getFileUrl())) {
                    return inputFileDto;
                }
            } catch (Exception e) {
                LOGGER.error("Error occurred while uploading. Error: ", e);
                throw new GCPFileUploadException("Error occurred while uploading");
            }


    return inputFiles.get(0);
    }
}