package com.netcracker.db.repository.impl;

import com.netcracker.db.entity.Contract;
import com.netcracker.db.repository.ContractRepository;
import com.netcracker.utils.ISorter;
import com.netcracker.utils.List;
import com.netcracker.utils.impl.BubbleSorter;
import com.netcracker.utils.impl.MyArrayList;

import java.util.Comparator;
import java.util.function.Predicate;

public class ContractRepositoryImpl implements ContractRepository {
    private List<Contract> contracts;
    private ISorter<Contract> sorter;

    public ContractRepositoryImpl() {
        contracts = new MyArrayList<>();
        sorter = new BubbleSorter<>();
    }

    public ContractRepositoryImpl(ISorter<Contract> sorter) {
        contracts = new MyArrayList<>();
        this.sorter = sorter;
    }

    public ContractRepositoryImpl(List<Contract> contracts, ISorter<Contract> sorter) {
        this.contracts = contracts;
        this.sorter = sorter;
    }

    /**
     * Saves the contract if it is not in the repository, updates if it exists.
     *
     * @param contract contract to be appended or updated to this repository
     */
    @Override
    public void save(Contract contract) {
        if(contract == null) return;

        for(int i = 0; i < contracts.size(); i++) {
            if(contract.getId().equals(contracts.get(i).getId())) {
                contracts.set(i, contract);
                return;
            }
        }
        contracts.add(contract);
    }

    /**
     * Saves contracts from the list that are not in the repository, updates which are.
     *
     * @param list list of contracts to be appended or updated to this repository
     */
    @Override
    public void saveAll(List<Contract> list) {
        for(int i = 0; i < list.size(); i++) {
            save(list.get(i));
        }
    }

    /**
     * Returns the contract with the specified identifier, or null if there is no such contract.
     *
     * @param id id of the contract to return
     * @return the contract with the specified identifier, or null if there is no such contract
     */
    @Override
    public Contract getById(Integer id) {
        for(int i = 0; i < contracts.size(); i++) {
            if(id.equals(contracts.get(i).getId())) {
                return contracts.get(i);
            }
        }
        return null;
    }

    /**
     * Remove the contract with the specified identifier and return true, or return false if there is no such contract.
     *
     * @param id id of the contract to remove
     * @return true if the contract has been deleted
     */
    @Override
    public boolean removeById(Integer id) {
        for(int i = 0; i < contracts.size(); i++) {
            if(id.equals(contracts.get(i).getId())) {
                contracts.remove(i);
                return true;
            }
        }
        return false;
    }

    public ContractRepositoryImpl getByPredicate(Predicate<Contract> p) {
        ContractRepositoryImpl newRep = new ContractRepositoryImpl();
        for (Contract c : contracts) {
            if (p.test(c)) {
                newRep.save(c);
            }
        }
        return null;
    }

    public ISorter<Contract> getSorter() {
        return sorter;
    }

    public void setSorter(ISorter<Contract> sorter) {
        this.sorter = sorter;
    }

    public void sort(Comparator<Contract> comparator) {
        sorter.sort(contracts, comparator);
    }

    public List<Contract> getContracts() {
        List<Contract> copy = new MyArrayList<>();
        for(Contract c : contracts) {
            copy.add(c.clone());
        }
        return copy;
    }
}
