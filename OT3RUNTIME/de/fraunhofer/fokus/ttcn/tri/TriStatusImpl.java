package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriStatus;


    public class TriStatusImpl implements TriStatus
    {
        
        int stat;

        // ---
        // constructors: see Framework xxx
        public TriStatusImpl()
        {
        }
        public TriStatusImpl(int status)
        {
           stat = status;
        }
        // ---

        public void setStatus(int status)
        {
            stat = status;
        }
        public int getStatus()
        {
            return stat;
        }
        public boolean equals(TriStatus status)
        {
            return status.getStatus() == stat;
        }
    }
