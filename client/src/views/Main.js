import React, { useState } from 'react'
import HomePage from './HomePage';
import About from './About';
import { Nav, Navbar } from 'react-bootstrap';

const Main = () => {
    const [activeTab, setActiveTab] = useState("0");
    const tabList = [{ id: "0", tabName: "Home" },
    { id: '1', tabName: "About" }
    ];
    const renderPage = () => {
        if (activeTab === "0") {
            return <HomePage />
        } else {
            return <About />
        }
    }
    const handleTabClick = (tabId) => {
        setActiveTab(tabId);
    }

    return (
        <>
            <Navbar bg="light" expand="lg" className="pt-3 ps-4">
                <div className="d-flex w-100 align-items-center justify-content-between">
                    {/* Nav tabs */}
                    <Nav variant="tabs" activeKey={activeTab}>
                        {tabList?.map((tab) => (
                            <Nav.Item key={tab.id}>
                                <Nav.Link
                                    eventKey={tab.id}
                                    onClick={() => handleTabClick(tab.id)}
                                    className="d-flex align-items-center"
                                >
                                    {tab.tabName}
                                </Nav.Link>
                            </Nav.Item>
                        ))}
                    </Nav>

                    {/* Right-aligned button */}
                    <button className="btn btn-success me-2">Login / Register</button>
                </div>
            </Navbar>
            {renderPage()}

        </>
    )
}

export default Main;
