/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.csdev.ebus.core.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christian Sowada - Initial contribution
 *
 */
public abstract class AbstractEBusConnection implements IEBusConnection {

    private static final Logger logger = LoggerFactory.getLogger(AbstractEBusConnection.class);

    /** output stream for eBus communication */
    protected OutputStream outputStream;

    /** input stream for eBus communication */
    protected InputStream inputStream;

    @Override
    public boolean close() throws IOException {

        if (outputStream != null) {
            outputStream.flush();
        }

        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);

        inputStream = null;
        outputStream = null;

        return true;
    }

    @Override
    public boolean isOpen() throws IOException {
        return inputStream != null;
    }

    @Override
    public int readBytes(byte[] buffer) throws IOException {
        return inputStream.read(buffer);
    }

    @Override
    public int readByte(boolean lowLatency) throws IOException {
        return inputStream.read();
    }

    @Override
    public boolean isReceiveBufferEmpty() throws IOException {
        return inputStream.available() == 0;
    }

    @Override
    public void writeByte(int b) throws IOException {
        outputStream.write(b);
        outputStream.flush();
    }

    @Override
    public void reset() throws IOException {
        int available = inputStream.available();
        if (available > 0) {
            logger.debug("InputBuffer is not empty before sending: {} byte(s) waiting !", available);
        }
        inputStream.skip(available);
    }

}
