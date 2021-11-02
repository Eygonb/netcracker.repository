package com.netcracker.db.repository;

import com.netcracker.db.entity.Contract;
import com.netcracker.utils.List;

public interface ContractRepository {
    /**
     * Saves the contract if it is not in the repository, updates if it exists.
     * @param contract contract to be appended or updated to this repository
     */
    void save(Contract contract);

    /**
     * Saves contracts from the list that are not in the repository, updates which are.
     * @param list list of contracts to be appended or updated to this repository
     */
    void saveAll(List<Contract> list);

    /**
     * Returns the contract with the specified identifier, or null if there is no such contract.
     * @param id id of the contract to return
     * @return the contract with the specified identifier, or null if there is no such contract
     */
    Contract getById(Integer id);

    /**
     * Remove the contract with the specified identifier and return true, or return false if there is no such contract.
     * @param id id of the contract to remove
     * @return true if the contract has been deleted
     */
    boolean removeById(Integer id);
}
