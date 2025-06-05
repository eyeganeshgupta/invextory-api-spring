package com.invextory.services.impl;

import com.invextory.dtos.SupplierDTO;
import com.invextory.dtos.response.Response;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.Supplier;
import com.invextory.repositories.SupplierRepository;
import com.invextory.services.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import static com.invextory.constants.AppText.*;

@Slf4j
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response addSupplier(SupplierDTO supplierDTO) {

        log.info(LOG_ADD_SUPPLIER_INIT, supplierDTO.getName());

        Supplier supplierToSave = modelMapper.map(supplierDTO, Supplier.class);
        supplierRepository.save(supplierToSave);

        log.info(LOG_ADD_SUPPLIER_SUCCESS, supplierDTO.getName());

        return Response.builder()
                .status(200)
                .message(SUPPLIER_SAVE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {

        log.info(LOG_UPDATE_SUPPLIER_INIT, id);

        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LOG_GET_SUPPLIER_BY_ID_INIT, id);
                    return new NotFoundException(SUPPLIER_NOT_FOUND_MESSAGE);
                });

        if (supplierDTO.getName() != null) {
            existingSupplier.setName(supplierDTO.getName());
        }
        if (supplierDTO.getContactInfo() != null) {
            existingSupplier.setContactInfo(supplierDTO.getContactInfo());
        }
        if (supplierDTO.getAddress() != null) {
            existingSupplier.setAddress(supplierDTO.getAddress());
        }

        supplierRepository.save(existingSupplier);

        log.info(LOG_UPDATE_SUPPLIER_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(SUPPLIER_UPDATE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response getAllSupplier() {
        return null;
    }

    @Override
    public Response getSupplierById(Long id) {
        return null;
    }

    @Override
    public Response deleteSupplier(Long id) {
        return null;
    }

}
