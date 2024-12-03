import React, { useState } from 'react'

const HomePage = () => {
    const [url, setUrl] = useState("");

    const handleClick = (e) => {
        e.preventDefault();
        console.log(url);
    }
    return (
        <div className='container mt-5'>
            <div className='row justify-content-center'>
                <div className='col-md-6'>
                    <div className='card shadow-sm'>
                        <div className='card-header'>Shorten a Long Link</div>
                        <div className='card-body'>
                            <form>
                                <label className='mb-1'>Paste your long link here</label>
                                <input
                                    className="form-control mb-3"
                                    value={url}
                                    onChange={(e) => setUrl(e.target.value)}
                                    placeholder='https://example.com/long-url'
                                />

                                <button
                                    className='btn btn-success w-100'
                                    type='button'
                                    onClick={handleClick}>Generate Short URL</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default HomePage