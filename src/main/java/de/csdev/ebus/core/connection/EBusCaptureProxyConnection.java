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

import de.csdev.ebus.utils.EmulatorCapture;

/**
 * @author Christian Sowada - Initial contribution
 *
 */
public class EBusCaptureProxyConnection implements IEBusConnection {

    private IEBusConnection proxyConnection;

    private EmulatorCapture captureWriter;

    public EBusCaptureProxyConnection(IEBusConnection proxyConnection, EmulatorCapture captureWriter) {
        this.proxyConnection = proxyConnection;
        this.captureWriter = captureWriter;
    }

    public boolean open() throws IOException {
        return proxyConnection.open();
    }

    public boolean close() throws IOException {
        captureWriter.close();
        return proxyConnection.close();
    }

    public boolean isOpen() throws IOException {
        return proxyConnection.isOpen();
    }

    public int readByte(boolean lowLatency) throws IOException {
        int readByte = proxyConnection.readByte(lowLatency);

        if (readByte != -1) {
            captureWriter.write(new byte[] { (byte) (readByte & 0xFF) });
        }

        return readByte;
    }

    public boolean isReceiveBufferEmpty() throws IOException {
        return proxyConnection.isReceiveBufferEmpty();
    }

    public int readBytes(byte[] buffer) throws IOException {
        int readBytes = proxyConnection.readBytes(buffer);
        captureWriter.write(buffer, readBytes);
        return readBytes;
    }

    public void writeByte(int b) throws IOException {
        proxyConnection.writeByte(b);
    }

    public void reset() throws IOException {
        proxyConnection.reset();
    }

}
